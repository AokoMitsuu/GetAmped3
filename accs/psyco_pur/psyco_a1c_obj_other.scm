	� G   t�       �VmoE��H����.wg'*7Hn���تT!��{�����ڍU"���UQ��*
�hB|�x�/�%����s���+�{f���ggg=�)��8j&�喬��-lU�7k#1��� aLG�)#V��5�;��i�,��8��C<tyG<q���X��#|�_��&�q�p��D.�^g��P'op��&���$��Z�m�k@ȱQ�>�HҮĈ��	�2�1v��(:�r<A�A��1"j�3I�&/,�@V�G𵹼�z^b�Iߣkq�ٝ���J�0_A1Mǜv�Rل@�&ʫ��h㝼$�<V�&�q@����%�u���H[�tm[���C���غsp#����%�m� �A�f=̌�����P6�� C4��nT��o��@�������
vN���.C�EYk��$C!c�F��DZ�r׆���'����ߔ,j��x�4K����\�i�Q���#j-�s;D&n��6�bqf�P
j��VO�y�=�" A�`�\�T�pL����-/���l{��������"}(�/�;8�D�����2���H?��"},�7vt 2��V�;@d�(��F��*�V�ma����%�~��mk�� .V������r$9�w��K"�X��`J�1��)-&�.��+� G-�6
&��Fgu��LϖA��n�N"�f��{ç;"�j�ɣ�������{��g ��aEzW��"�)wD�X�6����hy�d}�!��hJ%^9b빌�� s����Id̖M��ٲV �/i_<$P��l�l{�{s�ޖ|���d���1�` ��B�㲮�9q]�̩�^.+�1�����S֐Mgj�˹�u`"^�z�D��6����5S�MTv�g����(Oǩ'i;n�IzV$�xn����Tb5�.�{I��	O�^��m��ȏP���+�����UE���Y�e���2�O?�xm�u�-P;����k��Ku�T�R�=By�QTC��v�Ԏn���c�-o������E�$>��\=���r����Ru��b}q~iey��%��s�Y��z��霤=�xl0����W�?�r@���+�
���!�K�A�W��/���{S6��or��S�
   f  Lga2/stage/AmpedScript; e start psyco_explode psyco_explode_execute broken lockItemScript (ZZ)V
 f k l setShadowState
 d n 3 finishAllConnections
 d p 1
 H 0 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s tD /accs/psyco_pur/knife.scm�    ��      ?�  ?�  ?�               	start_obj lockTimeCount (Z)V
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
 � � � getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/KMath; � R2D (F)F
 � � � slopeY2 (F)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/Position; � dir	 � � 	 � R  getDir (Lkotori/geom/Vector3D;)F
 � � � Ljava/lang/Math; � round (F)I
 � � � length ()F
 � � � (FF)F
 � � � min
 � � �B4   getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � �B�   	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � � Lga2/stage/Attack; � startScript (Ljava/lang/String;)Z
 � � �-            �X� �D /accs/psyco_pur/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� D
��                        �  