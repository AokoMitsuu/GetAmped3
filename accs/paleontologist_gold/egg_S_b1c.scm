	� G   g�       �UMo�8���?p��*��&�Z�:H?�m7q��	�D�\�"K�S�E��)�_	����z|3�f8�v����ܒ�šV�p$5�o3#4$�'�'Bje�,(#+>O���>���`y��3`O�8�w>��6��I�*dT`w~o����,���5�#�H�&C�l��o�'�r����L?�\�.�]����������uM�3f���f%V����.����\��˄\�� !#������t�DNP��:~���[ಮ=��{��B3k� ����8g�����NVrf�y�N����v����/�M����,�}�J�*P�*��>/��f���,��3L�U�:�B��=�k�������3����/ը��*/$�`��Q,[L�)8��0�?JR�Q�Ns��}X�ʲG�Їk*�-��]g��-0kbA�W�ק�^T9}��� �e��S!��VU�����[y���DD8ose�F��3Nċ^�A����o��1��]{��#��Q������QYZ���
�"rS�� m��8!��s�-j`5�r�P�b@ ���	�Ex�i�B�ca���Ұ����IsQ���9�gި���
�l*���K��ô�҃]����0��W�>79�.�� ˖g�s�Q�A9���a4���+�߿_�i<��6������%s�t*����E�f���d\�����h7�b���pn�6��V�{t��&8K�2����v�5=��#38[Sg0MkZ'��M�J�帋����S�*[��p��u��1�=7�4���ç��"��H�ڝ�V2@�n��?2o��7�U�i)ʦD�n��CifԒW?}��4���$�6   j  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar2.agi�L3 start approachtarget broken lockItemScript (ZZ)V
 f m n
 H 0 3 clearGenerate
 d q 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s t setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d v wD /accs/paleontologist/egg.scm�    �.{    ?�  ?�  ?�               setJudgmentApproachTarget (F)V
 f z {@�   getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d ~  Lkotori/gfx/KGLScriptModel; � startScript (Ljava/lang/String;)Z
 � � � loop clearJudgmentKind
 f � 1 lockTimeCount (Z)V
 f � � approach-             getArgv ()Ljava/lang/Object;
 f � �-             getPosition ()Lkotori/geom/Position;
 f � �  .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � + � sub
 � � � Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D (F)F
 � � � ()F
 f � ��� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � �� d�B�D %/accs/paleontologist/raptor_S_b1c.scm�            ?�  ?�  ?�                 d�     d  �� 
 �       ,�>�        getAmpedModel ()Lga2/stage/AmpedModel;
 f � � MAT ()Lkotori/geom/Matrix;
 H � �  *(Lkotori/geom/Matrix;)Lkotori/geom/Matrix;
 � + � rotate .(Lkotori/geom/Quaternion;)Lkotori/geom/Matrix;
 � � � Lga2/stage/AmpedModel; � mat	 � �  setTranslate (FFF)Lkotori/geom/Matrix;
 � � �	 � Q 	 � R 	 � S  Lga2/stage/atk/AttackParam; � pmat	 � �  attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-    ?        exp kill
 f � 1 
stopScript
 f � 1 f h g - 1  i� �  j 1  R*� oW*d� pW*� oW*� rW**yj� u� xW*�� pW*}� |W*d� pW*� �� *� ��� �W�  k 1 *� �W*� oW*� �W*� pW*� �� *� ��� �W<� *d� pW�W���*� pW�M*� �N-� �N*� �:,-� �W,� �W,� �� �8*� �� �8f8�:� �W�:	*� �:

� 1� �:
� �� �� �W-� �-� �-� �� �W	� �*�	� �W*�� pW*� �� *� �ɶ �W* ȶ pW*� �W*� �W�  l 1  9*� �W*� oW*� �W*� �� *� �ɶ �W* ȶ pW*� �W*� �W� �^�k            =L��>L��=L���  