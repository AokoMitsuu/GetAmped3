	� G   ��       �SMkA�	)�?���F��MJ	9D[�K���"R���ɚݝ��$M�@�Q*x��z�oV�	�qH���wf�&����Kv��ϼυ׸�p�Q�r*&eN��(�^�M�����(!)C�H�����p��<��O����H�Kb b�z����Z���zz$�`)�K��
!q;7�T�:K��@�!�Y��xd�1�W$�q@ۍ0��1vY Etii����/-C��V]�ץ���!�Eۄ0y\'�M�FZ%��O���Ɇ^�m��K]'�z٬�y` M�/B5Q5q(�˶-;��wB
�ȏ���0nh���x��|� ͫA�vce����e��C>����|��t'��+��>=��!x��&N{8�K�Ra�#��"��P��0�M�w�!`4ڦ`la���S�$���,+����b]d(��A�>7�b� �'��3��]�51D"7���u�0TWNI��~Op�]��=�7	��&P����B��6�VO�����1$A�`+޹���X�i�!��x�`Y_�N^��>�l�go`�X������Ӄϣ�/y���>���;��,kt�����L=�2��ru,ꗫ�?x7��A��AP��$C�c�����{5P�)��P�\����v��\Q��S��̄�f�U1�<���Q]쵼��)�6�b�}-���8r�r1VՖL��tc6R���!����k;Ч   B  Lga2/stage/AmpedScript; e start sun_moon_explode sun_moon_explode_execute broken lockItemScript (ZZ)V
 f k l setShadowState
 d n 3 finishAllConnections
 d p 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d r sD !/accs/sun_moon/shoot_moon_obj.scm�            ?�  ?�  ?�  CH  B�       	start_obj
 H 0 3 lockTimeCount (Z)V
 f x y isServer ()Z
 f { | getNearestEnemyPos (I)[Lkotori/geom/Vector3D;
 f ~  '(Ljava/lang/Object;I)Ljava/lang/Object;
 H * � 
itemScript ((Ljava/lang/String;ILjava/lang/Object;)V
 f � � kill
 f � 1 
stopScript
 f � 1 	playSound
 d � 3 getArgv ()Ljava/lang/Object;
 f � ��� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � � getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � �B�   	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � � Lga2/stage/Attack; � startScript (Ljava/lang/String;)Z
 � � �-            � d�@�D !/accs/sun_moon/shoot_moon_obj.scm�            @   @   @       B�          d�    ^    .��       ,�>�         efx f  g 1  **� mW*� oW*� qW*uv� tW*d� wW*� mW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� wW*� �W*� �W�  i 1  Y*� qW*q� �W*� �L�M,� �M�,� �W*,� �W*��� ��� �W*� �W*�� wW*�� wW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� wW*� �W*� �W� R��M                        �  